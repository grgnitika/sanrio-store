package com.example.sanrio.Controller;


import com.example.sanrio.Entity.Cart;
import com.example.sanrio.Pojo.CartPojo;
import com.example.sanrio.Service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.sanrio.Pojo.CartUpdatePojo;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping(value="/save")
    public String saveCart(@Valid @RequestBody CartPojo cartPojo){
        cartService.saveCart(cartPojo);
        return "Saved";
    }

    @GetMapping("/getAll")
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){
      cartService.deleteById(id);
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<String> updateQuantity(@RequestBody CartUpdatePojo cartUpdatePojo) {
//        try {
            cartService.updateQuantity(cartUpdatePojo.getId(), cartUpdatePojo.getQuantity());
            return ResponseEntity.ok("Quantity updated successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to update quantity");
//        }
    }
}
