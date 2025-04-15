package service;

public interface ForgetPasswordService {
    Boolean forgetPassword(String account, String password) throws Exception;
}
