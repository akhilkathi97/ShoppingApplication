package com.shopping.controller;

import com.shopping.dao.ItemOrder;
import com.shopping.model.ItemDetails;
import com.shopping.model.UserDetails;
import com.shopping.repository.ItemDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("/item")
public class Items {
    @Autowired
    private ItemDetailsRepo itemDetailsRepo;

    @GetMapping("/all")
    public ResponseEntity getAllItems(){
        List<ItemDetails> items = itemDetailsRepo.findAll();
        return new ResponseEntity(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity getItemById(@PathVariable Long itemId){
        Optional<ItemDetails> items = itemDetailsRepo.findByItemId(itemId);
        if(items.isPresent()){
            return new ResponseEntity(items,HttpStatus.OK);
        }
        else{
            return new ResponseEntity("No Item with ID: "+itemId.toString(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{itemName}")
    public ResponseEntity getItemByItemName(@PathVariable String itemName){
        Optional<ItemDetails> items = itemDetailsRepo.findItemByItemName(itemName);
        if(items.isPresent()){
            return new ResponseEntity(items,HttpStatus.OK);
        }
        else{
            return new ResponseEntity("No Item with name: "+itemName,HttpStatus.NO_CONTENT);
        }
    }

    @Transactional
    @PostMapping("/orderItems")
    public ResponseEntity orderItem(@RequestParam List<ItemOrder> items, @RequestParam UserDetails user){
        HashMap <String,String> itemsUpdate = new HashMap<String,String>();
        if(user.getUserType()=="USER"){
            for(ItemOrder item:items){
                Optional<ItemDetails> itm = itemDetailsRepo.findByItemName(item.getItemName());
                if(itm.isPresent()){
                    long updatedQuantity = itm.get().getAvailableQuantity()- item.getQuantities();
                    if(updatedQuantity>=0){
                        itm.get().setAvailableQuantity(updatedQuantity);
                        itemDetailsRepo.save(itm.get());
                        itemsUpdate.put(item.getItemName(),"Ordered successfully for "+ item.getQuantities()+" quantities");
                    }
                    else{
                        itemsUpdate.put(item.getItemName(),"The available quantity is less than the ordered quantity");
                    }
                }
                else{
                    itemsUpdate.put(item.getItemName(),"No Item found");
                }

            }
            return new ResponseEntity(itemsUpdate,HttpStatus.OK);
        }
        return new ResponseEntity("Only users can make order",HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PostMapping("/manageItems")
    public ResponseEntity manageItem(@RequestParam List<ItemOrder> items, @RequestParam UserDetails user){
        HashMap <String,String> itemsUpdate = new HashMap<String,String>();
        if(user.getUserType()=="ADMIN"){
            for(ItemOrder item:items){
                Optional<ItemDetails> itm = itemDetailsRepo.findByItemName(item.getItemName());
                if(itm.isPresent()){
                    long updatedQuantity = itm.get().getAvailableQuantity() + item.getQuantities();
                    itm.get().setAvailableQuantity(updatedQuantity);
                    itemDetailsRepo.save(itm.get());
                    itemsUpdate.put(item.getItemName(),"Successfully updated "+ item.getItemName());
                }
                else{
                    ItemDetails newItem = new ItemDetails();
                    newItem.setItemName(item.getItemName());
                    newItem.setItemPrice(item.getPrice());
                    newItem.setAvailableQuantity(item.getQuantities());
                    itemDetailsRepo.save(newItem);
                    itemsUpdate.put(item.getItemName(),"No Item found");
                }

            }
            return new ResponseEntity(itemsUpdate,HttpStatus.OK);
        }
        return new ResponseEntity("Only admins can update inventory",HttpStatus.BAD_REQUEST);
    }
}
