package ua.com.usermanagementsystem.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
