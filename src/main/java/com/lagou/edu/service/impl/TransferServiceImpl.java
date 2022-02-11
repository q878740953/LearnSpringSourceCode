package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 应癫
 */
@Service("transferService")
public class TransferServiceImpl implements TransferService {

    @Autowired // @Autowired先通过类型查找，如果找到了多个实现了在通过名字去对比，当同一个接口多实现时可以配合@Qualifier注解按照id注入
    private AccountDao accountDao;

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);
//            int c = 1/0;
            accountDao.updateAccountByCardNo(from);

    }
}
