package lglearn.service.impl;

import lglearn.pojo.Account;
import lglearn.service.TransferService;
import lglearn.dao.AccountDao;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

    //Autowired 按照类型注入 如果区分不出来需要结合Qualifier
    @Autowired
    @Qualifier("accountDao")
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
