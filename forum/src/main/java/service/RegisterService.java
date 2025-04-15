package service;

public interface RegisterService {
    Boolean register(String phone, String email, String password) throws Exception;
}
