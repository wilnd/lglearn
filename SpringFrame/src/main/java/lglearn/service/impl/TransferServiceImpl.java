package lglearn.service.impl;

import lglearn.pojo.Account;
import lglearn.service.TransferService;
import lglearn.dao.AccountDao;
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
//        int c = 1 / 0;
        accountDao.updateAccountByCardNo(to);
    }
}
