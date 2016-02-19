package com.aishang.app.ui.ExchangeHouse;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.aishang.app.R;
import com.aishang.app.ui.base.BaseActivity;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class ExchangeHouseActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.expandable_text) TextView expandableText;
  @Bind(R.id.expand_collapse) ImageButton expandCollapse;
  @Bind(R.id.expand_text_view) ExpandableTextView expandTextView;
  @Bind(R.id.layoutRoot) RelativeLayout layoutRoot;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_exchange_house);
    ButterKnife.bind(this);

    expandTextView.setText(
        "爱尚旅居——中国首个共权度假屋共享换住平台，由重庆耘目实业有限公司倾力打造，提供共权度假屋网上展示、申购、预订、换住以及出租、出售服务的共权度假屋综合服务网络平台。\n"
            + "\"\n"
            + "            + \"度假交换服务\\n\"\n"
            + "            + \"1、乙方将房屋旺季的使用权交给甲方，由甲方进行运营，鉴于乙方房屋所处的区位及气候特点，人气居住率，运营等综合因素，甲方平台能接受的乙方房屋可交换权益积分。\\n\"\n"
            + "            + \"2、甲方提供乙方在爱尚旅居平台免费度假交换权益。乙方登陆爱尚旅居平台账户后，仅需根据度假需求选择物业，凭积分预订入住即可。 (注：积分换算公式 区域一个间夜价格X天数=换住积分。例 当地房屋间夜价格为300元/晚，权益为20天，则积分为：300X20=6000积分)\\n\"\n"
            + "            + \"3、甲方运营期间，所产生的水、电、燃气、有线电视、电话、宽带等费用，由甲方承担。 注意：因为甲方已经给予乙方每年固定的交换度假权益，甲方运营期间内，房屋使用权益归甲方所有乙方不得入住。期间运营亏损归甲方承担，同理如产生收益也归甲方所有。");
  }
}
