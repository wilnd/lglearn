package com.lglearn.service.impl;

import com.lglearn.dao.AccountDao;
import com.lglearn.pojo.Account;
import com.lglearn.service.TransferService;
import com.lglearn.utils.ConnectionUtil;
import com.lglearn.utils.TransactionManager;
import lombok.Setter;

public class TransferServiceImpl implements TransferService {

    @Setter
    private AccountDao accountDao;

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);
        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);
        accountDao.updateAccountByCardNo(from);
        int c = 1 / 0;
        accountDao.updateAccountByCardNo(to);
    }
}
