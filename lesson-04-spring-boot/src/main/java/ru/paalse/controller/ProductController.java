package ru.paalse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.paalse.persist.Product;
import ru.paalse.persist.ProductRepository;
import ru.paalse.service.ProductRepr;
import ru.paalse.service.ProductService;
import ru.paalse.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model){
        logger.info("List products page requested");
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page product for id{} requested", id);

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid  @ModelAttribute("product") ProductRepr product, BindingResult result){
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }

        logger.info("Updating product with id{}", product.getId());
        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new product request");
        model.addAttribute("product", new Product());
        return "product_form";
    }

//    @GetMapping("/{id}/delete")
//    public String remove(@PathVariable("id") Long id) {
//        productRepository.delete(id);
//        return "redirect:/product";
//    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Delete product with id {} ", id);
        productService.delete(id);
        return "redirect:/product";
    }
}