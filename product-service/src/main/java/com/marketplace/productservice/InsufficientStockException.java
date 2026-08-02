package com.marketplace.productservice;
class InsufficientStockException extends RuntimeException { InsufficientStockException(Long id,int available){super("Estoque insuficiente para o produto "+id+". Disponível: "+available);} }
