package com.example.ecommerce.controllers;

import com.example.ecommerce.mappers.impl.AddressMapperImpl;
import com.example.ecommerce.models.DTO.AddressDTO;
import com.example.ecommerce.models.entities.Address;
import com.example.ecommerce.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final AddressMapperImpl addressMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getOneAddress(@PathVariable("id") Integer id) {
        Address address = addressService.findOne(id).orElse(null);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(addressMapper.mapTo(address), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public List<AddressDTO> getAllAddressesByUserId(@PathVariable("userId") String userId) {
        List<Address> addresses = addressService.findAllByUserId(userId);

        return addresses.stream().map(addressMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.mapFrom(addressDTO);
        Address createdAddress = addressService.createAndUpdate(address);
        return new ResponseEntity<>(addressMapper.mapTo(createdAddress), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") Integer id, @RequestBody AddressDTO addressDTO) {
        Address address = addressMapper.mapFrom(addressDTO);
        address.setId(id);
        Address updatedAddress = addressService.createAndUpdate(address);
        return new ResponseEntity<>(addressMapper.mapTo(updatedAddress), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer id) {
        if (!addressService.isExist(id)) {
            return ResponseEntity.notFound().build();
        }
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
