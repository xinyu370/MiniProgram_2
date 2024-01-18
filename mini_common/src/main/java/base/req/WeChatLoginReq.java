package base.req;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WeChatLoginReq implements Serializable {

    @ApiModelProperty("微信临时登录凭证")
    private String loginCredit;
    @ApiModelProperty("openId")
    private String openId;
    @ApiModelProperty("微信头像")
    private String headPic;
    @ApiModelProperty("微信昵称")
    private String nickName;
}
