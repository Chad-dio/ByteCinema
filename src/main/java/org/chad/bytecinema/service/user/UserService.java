package org.chad.bytecinema.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.chad.bytecinema.domain.po.User;
import org.chad.bytecinema.domain.vo.FansPageInfoVO;

public interface UserService extends IService<User> {
    Boolean followOrUnfollow(String followId);

    IPage<FansPageInfoVO> getFans(String userId, Integer pageNo, Integer pageSize);
}
