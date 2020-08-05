package com.louay.model.service.userpic;

import com.louay.model.entity.users.picute.AccountPicture;

public interface AccountPictureService {
    AccountPicture createAccountPicture(AccountPicture accountPicture);

    AccountPicture deleteAccountPictureByUserId(AccountPicture accountPicture);

    AccountPicture updateAccountPicture(AccountPicture accountPicture);

    AccountPicture findAccountPictureByUserId(AccountPicture accountPicture);
}
