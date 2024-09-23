package com.distribution.moneydistribution.domain.security;

import com.distribution.moneydistribution.domain.user.User;

public interface SecurityService {

    User getCurrentUser();

    Long getCurrentUserId();

    String getCurrentUserEmail();

    String getCurrentUserNickName();

    String isVip();

}
