package com.lglearn.service.impl;

import com.lglearn.dao.AccountDao;
import com.lglearn.pojo.Account;
import com.lglearn.service.TransferService;
import lombok.Setter;

public class TransferServiceImpl implements TransferService {

    @Setter
    private AccountDao accountDao;

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money)
            throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);
        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);
        accountDao.updateAccountByCardNo(from);
        accountDao.updateAccountByCardNo(to);
    }
}
