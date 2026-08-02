package com.marketplace.domain.category;
import java.util.*;
public interface CategoryRepository { Category save(Category category); Optional<Category> findById(Long id); List<Category> findActive(); }
