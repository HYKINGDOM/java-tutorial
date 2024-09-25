# CreatorInfoController

CreatorInfoController


---
## 通过GET请求获取创作者信息

> BASIC

**Path:** /getCreatorInfo

**Method:** GET

> REQUEST

**Query:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| creatorId |  | YES | 创作者的ID，用于标识需要获取信息的创作者 |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| success | boolean | 是否成功 |
| code | string | 响应码 |
| msg | string | 消息 |
| traceId | string |  |
| data | object | 数据 |
| &ensp;&ensp;&#124;─creatorId | string | 达人ID |
| &ensp;&ensp;&#124;─creatorName | string | 达人名称 |
| &ensp;&ensp;&#124;─creatorNickname | string | 达人昵称 |
| &ensp;&ensp;&#124;─creatorPortrait | string | 达人头像 |
| &ensp;&ensp;&#124;─mcnContract | string | MCN签约 |
| &ensp;&ensp;&#124;─creatorCategories | string | 达人标签 |
| &ensp;&ensp;&#124;─ecommerceType | string | 带货方式 |
| &ensp;&ensp;&#124;─creatorVerification | string | 达人认证 |
| &ensp;&ensp;&#124;─description | string | 达人简介 |
| &ensp;&ensp;&#124;─homepage | string | 主页地址 |
| &ensp;&ensp;&#124;─email | string | 邮箱 |
| &ensp;&ensp;&#124;─country | string | 达人国家 |
| &ensp;&ensp;&#124;─capName | string | 机构名称 |
| &ensp;&ensp;&#124;─addTime | string | 添加日期 |
| &ensp;&ensp;&#124;─sourcePlatform | string | 来源平台 |
| &ensp;&ensp;&#124;─fansNum | integer | 粉丝数 |
| &ensp;&ensp;&#124;─followers | integer | 累计关注数 |
| &ensp;&ensp;&#124;─likes | integer | 累计点赞数 |
| &ensp;&ensp;&#124;─videos | integer | 累计视频数 |
| &ensp;&ensp;&#124;─livestreamings | integer | 累计带货直播数 |
| &ensp;&ensp;&#124;─videoGpmMin | number | 视频GPM-最小 |
| &ensp;&ensp;&#124;─videoGpmMax | number | 视频GPM-最大 |
| &ensp;&ensp;&#124;─liveGpmMin | integer | 直播GPM-最小 |
| &ensp;&ensp;&#124;─liveGpmMax | integer | 直播GPM-最大 |
| &ensp;&ensp;&#124;─nationalRank | integer | 本国排名 |
| &ensp;&ensp;&#124;─relatedShops | integer | 累计关联店铺数 |
| &ensp;&ensp;&#124;─promotedProducts | integer | 累计带货商品数 |
| &ensp;&ensp;&#124;─productCategoriesTop1 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop1En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop1 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop2 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop2En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop2 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop3 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop3En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop3 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop4 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop4En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop4 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop5 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop5En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop5 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─fans1824Percent | integer | 粉丝18-24年龄占比 |
| &ensp;&ensp;&#124;─fans2534Percent | integer | 粉丝25-34年龄占比 |
| &ensp;&ensp;&#124;─fans35AbovePercent | integer | 粉丝35+年龄占比 |
| &ensp;&ensp;&#124;─femalePercent | integer | 女性占比 |
| &ensp;&ensp;&#124;─malePercent | integer | 男性占比 |
| &ensp;&ensp;&#124;─recent30VideosViews | integer | 近30条视频播放数 |
| &ensp;&ensp;&#124;─recent30VideosLikes | integer | 近30条视频点赞数 |
| &ensp;&ensp;&#124;─recent30VideosComments | integer | 近30条视频评论数 |
| &ensp;&ensp;&#124;─recent30VideosShares | integer | 近30条视频分享数 |
| &ensp;&ensp;&#124;─recent30DaysGmv | integer | 近30天GMV |
| &ensp;&ensp;&#124;─videoslikesGained7days | integer | 近7日粉丝增量 |
| &ensp;&ensp;&#124;─ecommerceLives7days | integer | 近7日带货直播数 |
| &ensp;&ensp;&#124;─videos7days | integer | 近7日视频数 |
| &ensp;&ensp;&#124;─followersGained7days | integer | 近7日视频点赞增量 |
| &ensp;&ensp;&#124;─videoIpm7days | integer | 近7日视频IPM |
| &ensp;&ensp;&#124;─ecommerceVideos28days | integer | 近28天带货视频数 |
| &ensp;&ensp;&#124;─productsPromotedInVideos28days | integer | 近28天视频带货商品数 |
| &ensp;&ensp;&#124;─fansMainAge | string | 粉丝主要年龄(18-24,25-34,35+) |
| &ensp;&ensp;&#124;─fansMainSex | string | 粉丝主要性别(女,男) |
| &ensp;&ensp;&#124;─videoGmv28days | number | 近28天视频带货总GMV |
| &ensp;&ensp;&#124;─liveGmv28days | number | 近28天直播带货总GMV |
| &ensp;&ensp;&#124;─totalGmv28days | number | 近28天带货总GMV |
| &ensp;&ensp;&#124;─productEcommerceOrdersMax | number | 最大销量 |
| &ensp;&ensp;&#124;─crawTime | string | 爬取时间 |
| &ensp;&ensp;&#124;─updateTime | string |  |
| &ensp;&ensp;&#124;─totalGmvAll | number | 累计带货总GMV |
| &ensp;&ensp;&#124;─ecommerceVideos | integer | 累计带货视频数 |
| &ensp;&ensp;&#124;─ecommerceVideos60days | integer | 近60天带货视频数 |
| &ensp;&ensp;&#124;─recent60DaysGmv | number | 近60天视频带货GMV |
| &ensp;&ensp;&#124;─ecommerceVideos90days | integer | 近90天带货视频数 |
| &ensp;&ensp;&#124;─recent90DaysGmv | number | 近90天视频带货GMV |
| &ensp;&ensp;&#124;─instagramUsername | string | instagram账号名称 |
| &ensp;&ensp;&#124;─instagramLink | string | instagram主页 |
| &ensp;&ensp;&#124;─youtubeLink | string | youtube主页 |
| &ensp;&ensp;&#124;─youtubeChannelTitle | string | 所属youtube频道 |
| &ensp;&ensp;&#124;─boundStatus | string | 是否绑定(已绑定) |
| &ensp;&ensp;&#124;─isCommissionDaren | integer | 是否分佣达人:0不分佣，1分佣 |
| &ensp;&ensp;&#124;─videoGmv7days | number | 近7天视频带货总GMV |
| &ensp;&ensp;&#124;─totalGmv7days | number | 近7天带货总GMV |
| &ensp;&ensp;&#124;─videoGmv14days | number | 近14天视频带货总GMV |
| &ensp;&ensp;&#124;─totalGmv14days | number | 近14天带货总GMV |
| &ensp;&ensp;&#124;─medianWatchPvVideo7d | integer | 近7天视频播放中位数 |
| &ensp;&ensp;&#124;─medianWatchPvVideo14d | integer | 近14天视频播放中位数 |
| &ensp;&ensp;&#124;─medianWatchPvVideo28d | integer | 近28天视频播放中位数 |

**Response Demo:**

```json
{
  "success": false,
  "code": "",
  "msg": "",
  "traceId": "",
  "data": {
    "creatorId": "",
    "creatorName": "",
    "creatorNickname": "",
    "creatorPortrait": "",
    "mcnContract": "",
    "creatorCategories": "",
    "ecommerceType": "",
    "creatorVerification": "",
    "description": "",
    "homepage": "",
    "email": "",
    "country": "",
    "capName": "",
    "addTime": "",
    "sourcePlatform": "",
    "fansNum": 0,
    "followers": 0,
    "likes": 0,
    "videos": 0,
    "livestreamings": 0,
    "videoGpmMin": 0.0,
    "videoGpmMax": 0.0,
    "liveGpmMin": 0,
    "liveGpmMax": 0,
    "nationalRank": 0,
    "relatedShops": 0,
    "promotedProducts": 0,
    "productCategoriesTop1": "",
    "productCategoriesTop1En": "",
    "productCategoriesProportionTop1": 0,
    "productCategoriesTop2": "",
    "productCategoriesTop2En": "",
    "productCategoriesProportionTop2": 0,
    "productCategoriesTop3": "",
    "productCategoriesTop3En": "",
    "productCategoriesProportionTop3": 0,
    "productCategoriesTop4": "",
    "productCategoriesTop4En": "",
    "productCategoriesProportionTop4": 0,
    "productCategoriesTop5": "",
    "productCategoriesTop5En": "",
    "productCategoriesProportionTop5": 0,
    "fans1824Percent": 0,
    "fans2534Percent": 0,
    "fans35AbovePercent": 0,
    "femalePercent": 0,
    "malePercent": 0,
    "recent30VideosViews": 0,
    "recent30VideosLikes": 0,
    "recent30VideosComments": 0,
    "recent30VideosShares": 0,
    "recent30DaysGmv": 0,
    "videoslikesGained7days": 0,
    "ecommerceLives7days": 0,
    "videos7days": 0,
    "followersGained7days": 0,
    "videoIpm7days": 0,
    "ecommerceVideos28days": 0,
    "productsPromotedInVideos28days": 0,
    "fansMainAge": "",
    "fansMainSex": "",
    "videoGmv28days": 0.0,
    "liveGmv28days": 0.0,
    "totalGmv28days": 0.0,
    "productEcommerceOrdersMax": 0.0,
    "crawTime": "",
    "updateTime": "",
    "totalGmvAll": 0.0,
    "ecommerceVideos": 0,
    "ecommerceVideos60days": 0,
    "recent60DaysGmv": 0.0,
    "ecommerceVideos90days": 0,
    "recent90DaysGmv": 0.0,
    "instagramUsername": "",
    "instagramLink": "",
    "youtubeLink": "",
    "youtubeChannelTitle": "",
    "boundStatus": "",
    "isCommissionDaren": 0,
    "videoGmv7days": 0.0,
    "totalGmv7days": 0.0,
    "videoGmv14days": 0.0,
    "totalGmv14days": 0.0,
    "medianWatchPvVideo7d": 0,
    "medianWatchPvVideo14d": 0,
    "medianWatchPvVideo28d": 0
  }
}
```




---
## 根据国家名称查询CreatorInfo分页数据

> BASIC

**Path:** /findByCountry

**Method:** GET

> REQUEST

**Query:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| country |  | YES | 国家名称，用于筛选CreatorInfo |
| pageNumber |  | YES | 页码，用于分页查询 |



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| success | boolean | 是否成功 |
| code | string | 响应码 |
| msg | string | 消息 |
| traceId | string |  |
| data | object | 数据 |
| &ensp;&ensp;&#124;─content | array |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorId | string | 达人ID |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorName | string | 达人名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorNickname | string | 达人昵称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorPortrait | string | 达人头像 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─mcnContract | string | MCN签约 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorCategories | string | 达人标签 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceType | string | 带货方式 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─creatorVerification | string | 达人认证 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─description | string | 达人简介 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─homepage | string | 主页地址 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─email | string | 邮箱 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─country | string | 达人国家 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─capName | string | 机构名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─addTime | string | 添加日期 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─sourcePlatform | string | 来源平台 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fansNum | integer | 粉丝数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─followers | integer | 累计关注数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─likes | integer | 累计点赞数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videos | integer | 累计视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─livestreamings | integer | 累计带货直播数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoGpmMin | number | 视频GPM-最小 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoGpmMax | number | 视频GPM-最大 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─liveGpmMin | integer | 直播GPM-最小 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─liveGpmMax | integer | 直播GPM-最大 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─nationalRank | integer | 本国排名 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─relatedShops | integer | 累计关联店铺数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─promotedProducts | integer | 累计带货商品数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop1 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop1En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesProportionTop1 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop2 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop2En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesProportionTop2 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop3 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop3En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesProportionTop3 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop4 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop4En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesProportionTop4 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop5 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesTop5En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productCategoriesProportionTop5 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fans1824Percent | integer | 粉丝18-24年龄占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fans2534Percent | integer | 粉丝25-34年龄占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fans35AbovePercent | integer | 粉丝35+年龄占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─femalePercent | integer | 女性占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─malePercent | integer | 男性占比 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent30VideosViews | integer | 近30条视频播放数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent30VideosLikes | integer | 近30条视频点赞数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent30VideosComments | integer | 近30条视频评论数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent30VideosShares | integer | 近30条视频分享数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent30DaysGmv | integer | 近30天GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoslikesGained7days | integer | 近7日粉丝增量 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceLives7days | integer | 近7日带货直播数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videos7days | integer | 近7日视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─followersGained7days | integer | 近7日视频点赞增量 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoIpm7days | integer | 近7日视频IPM |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceVideos28days | integer | 近28天带货视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productsPromotedInVideos28days | integer | 近28天视频带货商品数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fansMainAge | string | 粉丝主要年龄(18-24,25-34,35+) |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─fansMainSex | string | 粉丝主要性别(女,男) |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoGmv28days | number | 近28天视频带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─liveGmv28days | number | 近28天直播带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─totalGmv28days | number | 近28天带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─productEcommerceOrdersMax | number | 最大销量 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─crawTime | string | 爬取时间 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─updateTime | string |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─totalGmvAll | number | 累计带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceVideos | integer | 累计带货视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceVideos60days | integer | 近60天带货视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent60DaysGmv | number | 近60天视频带货GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ecommerceVideos90days | integer | 近90天带货视频数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─recent90DaysGmv | number | 近90天视频带货GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─instagramUsername | string | instagram账号名称 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─instagramLink | string | instagram主页 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─youtubeLink | string | youtube主页 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─youtubeChannelTitle | string | 所属youtube频道 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─boundStatus | string | 是否绑定(已绑定) |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─isCommissionDaren | integer | 是否分佣达人:0不分佣，1分佣 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoGmv7days | number | 近7天视频带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─totalGmv7days | number | 近7天带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─videoGmv14days | number | 近14天视频带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─totalGmv14days | number | 近14天带货总GMV |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─medianWatchPvVideo7d | integer | 近7天视频播放中位数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─medianWatchPvVideo14d | integer | 近14天视频播放中位数 |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─medianWatchPvVideo28d | integer | 近28天视频播放中位数 |
| &ensp;&ensp;&#124;─pageable | object |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─paged | boolean | Returns whether the current{@link Pageable} contains pagination information. |
| &ensp;&ensp;&ensp;&ensp;&#124;─unpaged | boolean | Returns whether the current{@link Pageable} does not contain pagination information. |
| &ensp;&ensp;&ensp;&ensp;&#124;─pageNumber | integer | Returns the page to be returned. |
| &ensp;&ensp;&ensp;&ensp;&#124;─pageSize | integer | Returns the number of items to be returned. |
| &ensp;&ensp;&ensp;&ensp;&#124;─offset | integer | Returns the offset to be taken according to the underlying page and page size. |
| &ensp;&ensp;&ensp;&ensp;&#124;─sort | array | Returns the sorting parameters. |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─direction | string | ASC :ASC<br>DESC :DESC |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─property | string |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ignoreCase | boolean |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─nullHandling | string | NATIVE :Lets the data store decide what to do with nulls.<br>NULLS_FIRST :A hint to the used data store to order entries with null values before non null entries.<br>NULLS_LAST :A hint to the used data store to order entries with null values after non null entries. |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ascending | boolean | Returns whether sorting for this property shall be ascending. |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─descending | boolean | Returns whether sorting for this property shall be descending. |
| &ensp;&ensp;&#124;─total | integer |  |
| &ensp;&ensp;&#124;─empty | boolean | Returns whether the current{@link Streamable} is empty. |
| &ensp;&ensp;&#124;─number | integer |  |
| &ensp;&ensp;&#124;─size | integer |  |
| &ensp;&ensp;&#124;─numberOfElements | integer |  |
| &ensp;&ensp;&#124;─sort | array |  |
| &ensp;&ensp;&ensp;&ensp;&#124;─ | object |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─direction | string | ASC :ASC<br>DESC :DESC |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─property | string |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ignoreCase | boolean |  |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─nullHandling | string | NATIVE :Lets the data store decide what to do with nulls.<br>NULLS_FIRST :A hint to the used data store to order entries with null values before non null entries.<br>NULLS_LAST :A hint to the used data store to order entries with null values after non null entries. |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─ascending | boolean | Returns whether sorting for this property shall be ascending. |
| &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&#124;─descending | boolean | Returns whether sorting for this property shall be descending. |
| &ensp;&ensp;&#124;─first | boolean |  |
| &ensp;&ensp;&#124;─last | boolean |  |
| &ensp;&ensp;&#124;─totalPages | integer |  |
| &ensp;&ensp;&#124;─totalElements | integer |  |

**Response Demo:**

```json
{
  "success": false,
  "code": "",
  "msg": "",
  "traceId": "",
  "data": {
    "content": [
      {
        "creatorId": "",
        "creatorName": "",
        "creatorNickname": "",
        "creatorPortrait": "",
        "mcnContract": "",
        "creatorCategories": "",
        "ecommerceType": "",
        "creatorVerification": "",
        "description": "",
        "homepage": "",
        "email": "",
        "country": "",
        "capName": "",
        "addTime": "",
        "sourcePlatform": "",
        "fansNum": 0,
        "followers": 0,
        "likes": 0,
        "videos": 0,
        "livestreamings": 0,
        "videoGpmMin": 0.0,
        "videoGpmMax": 0.0,
        "liveGpmMin": 0,
        "liveGpmMax": 0,
        "nationalRank": 0,
        "relatedShops": 0,
        "promotedProducts": 0,
        "productCategoriesTop1": "",
        "productCategoriesTop1En": "",
        "productCategoriesProportionTop1": 0,
        "productCategoriesTop2": "",
        "productCategoriesTop2En": "",
        "productCategoriesProportionTop2": 0,
        "productCategoriesTop3": "",
        "productCategoriesTop3En": "",
        "productCategoriesProportionTop3": 0,
        "productCategoriesTop4": "",
        "productCategoriesTop4En": "",
        "productCategoriesProportionTop4": 0,
        "productCategoriesTop5": "",
        "productCategoriesTop5En": "",
        "productCategoriesProportionTop5": 0,
        "fans1824Percent": 0,
        "fans2534Percent": 0,
        "fans35AbovePercent": 0,
        "femalePercent": 0,
        "malePercent": 0,
        "recent30VideosViews": 0,
        "recent30VideosLikes": 0,
        "recent30VideosComments": 0,
        "recent30VideosShares": 0,
        "recent30DaysGmv": 0,
        "videoslikesGained7days": 0,
        "ecommerceLives7days": 0,
        "videos7days": 0,
        "followersGained7days": 0,
        "videoIpm7days": 0,
        "ecommerceVideos28days": 0,
        "productsPromotedInVideos28days": 0,
        "fansMainAge": "",
        "fansMainSex": "",
        "videoGmv28days": 0.0,
        "liveGmv28days": 0.0,
        "totalGmv28days": 0.0,
        "productEcommerceOrdersMax": 0.0,
        "crawTime": "",
        "updateTime": "",
        "totalGmvAll": 0.0,
        "ecommerceVideos": 0,
        "ecommerceVideos60days": 0,
        "recent60DaysGmv": 0.0,
        "ecommerceVideos90days": 0,
        "recent90DaysGmv": 0.0,
        "instagramUsername": "",
        "instagramLink": "",
        "youtubeLink": "",
        "youtubeChannelTitle": "",
        "boundStatus": "",
        "isCommissionDaren": 0,
        "videoGmv7days": 0.0,
        "totalGmv7days": 0.0,
        "videoGmv14days": 0.0,
        "totalGmv14days": 0.0,
        "medianWatchPvVideo7d": 0,
        "medianWatchPvVideo14d": 0,
        "medianWatchPvVideo28d": 0
      }
    ],
    "pageable": {
      "paged": false,
      "unpaged": false,
      "pageNumber": 0,
      "pageSize": 0,
      "offset": 0,
      "sort": [
        {
          "direction": "",
          "property": "",
          "ignoreCase": false,
          "nullHandling": "",
          "ascending": false,
          "descending": false
        }
      ]
    },
    "total": 0,
    "empty": false,
    "number": 0,
    "size": 0,
    "numberOfElements": 0,
    "sort": [
      {
        "direction": "",
        "property": "",
        "ignoreCase": false,
        "nullHandling": "",
        "ascending": false,
        "descending": false
      }
    ],
    "first": false,
    "last": false,
    "totalPages": 0,
    "totalElements": 0
  }
}
```




---
## 查询所有创建者的ID

> BASIC

**Path:** /queryAllCreatorId

**Method:** GET

> REQUEST



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| success | boolean | 是否成功 |
| code | string | 响应码 |
| msg | string | 消息 |
| traceId | string |  |
| data | array | 数据 |
| &ensp;&ensp;&#124;─ | string |  |

**Response Demo:**

```json
{
  "success": false,
  "code": "",
  "msg": "",
  "traceId": "",
  "data": [
    ""
  ]
}
```




---
## 更新创作者信息

> BASIC

**Path:** /updateCreatorInfo

**Method:** POST

> REQUEST

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| Content-Type | application/json | YES |  |

**Request Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| creatorId | string | 达人ID |
| creatorName | string | 达人名称 |
| creatorNickname | string | 达人昵称 |
| creatorPortrait | string | 达人头像 |
| mcnContract | string | MCN签约 |
| creatorCategories | string | 达人标签 |
| ecommerceType | string | 带货方式 |
| creatorVerification | string | 达人认证 |
| description | string | 达人简介 |
| homepage | string | 主页地址 |
| email | string | 邮箱 |
| country | string | 达人国家 |
| capName | string | 机构名称 |
| addTime | string | 添加日期 |
| sourcePlatform | string | 来源平台 |
| fansNum | integer | 粉丝数 |
| followers | integer | 累计关注数 |
| likes | integer | 累计点赞数 |
| videos | integer | 累计视频数 |
| livestreamings | integer | 累计带货直播数 |
| videoGpmMin | number | 视频GPM-最小 |
| videoGpmMax | number | 视频GPM-最大 |
| liveGpmMin | integer | 直播GPM-最小 |
| liveGpmMax | integer | 直播GPM-最大 |
| nationalRank | integer | 本国排名 |
| relatedShops | integer | 累计关联店铺数 |
| promotedProducts | integer | 累计带货商品数 |
| productCategoriesTop1 | string | 达人TOP1带货类目名称 |
| productCategoriesTop1En | string | 达人TOP1带货类目名称 |
| productCategoriesProportionTop1 | integer | 达人TOP1带货类目占比 |
| productCategoriesTop2 | string | 达人TOP1带货类目名称 |
| productCategoriesTop2En | string | 达人TOP1带货类目名称 |
| productCategoriesProportionTop2 | integer | 达人TOP1带货类目占比 |
| productCategoriesTop3 | string | 达人TOP1带货类目名称 |
| productCategoriesTop3En | string | 达人TOP1带货类目名称 |
| productCategoriesProportionTop3 | integer | 达人TOP1带货类目占比 |
| productCategoriesTop4 | string | 达人TOP1带货类目名称 |
| productCategoriesTop4En | string | 达人TOP1带货类目名称 |
| productCategoriesProportionTop4 | integer | 达人TOP1带货类目占比 |
| productCategoriesTop5 | string | 达人TOP1带货类目名称 |
| productCategoriesTop5En | string | 达人TOP1带货类目名称 |
| productCategoriesProportionTop5 | integer | 达人TOP1带货类目占比 |
| fans1824Percent | integer | 粉丝18-24年龄占比 |
| fans2534Percent | integer | 粉丝25-34年龄占比 |
| fans35AbovePercent | integer | 粉丝35+年龄占比 |
| femalePercent | integer | 女性占比 |
| malePercent | integer | 男性占比 |
| recent30VideosViews | integer | 近30条视频播放数 |
| recent30VideosLikes | integer | 近30条视频点赞数 |
| recent30VideosComments | integer | 近30条视频评论数 |
| recent30VideosShares | integer | 近30条视频分享数 |
| recent30DaysGmv | integer | 近30天GMV |
| videoslikesGained7days | integer | 近7日粉丝增量 |
| ecommerceLives7days | integer | 近7日带货直播数 |
| videos7days | integer | 近7日视频数 |
| followersGained7days | integer | 近7日视频点赞增量 |
| videoIpm7days | integer | 近7日视频IPM |
| ecommerceVideos28days | integer | 近28天带货视频数 |
| productsPromotedInVideos28days | integer | 近28天视频带货商品数 |
| fansMainAge | string | 粉丝主要年龄(18-24,25-34,35+) |
| fansMainSex | string | 粉丝主要性别(女,男) |
| videoGmv28days | number | 近28天视频带货总GMV |
| liveGmv28days | number | 近28天直播带货总GMV |
| totalGmv28days | number | 近28天带货总GMV |
| productEcommerceOrdersMax | number | 最大销量 |
| crawTime | string | 爬取时间 |
| updateTime | string |  |
| totalGmvAll | number | 累计带货总GMV |
| ecommerceVideos | integer | 累计带货视频数 |
| ecommerceVideos60days | integer | 近60天带货视频数 |
| recent60DaysGmv | number | 近60天视频带货GMV |
| ecommerceVideos90days | integer | 近90天带货视频数 |
| recent90DaysGmv | number | 近90天视频带货GMV |
| instagramUsername | string | instagram账号名称 |
| instagramLink | string | instagram主页 |
| youtubeLink | string | youtube主页 |
| youtubeChannelTitle | string | 所属youtube频道 |
| boundStatus | string | 是否绑定(已绑定) |
| isCommissionDaren | integer | 是否分佣达人:0不分佣，1分佣 |
| videoGmv7days | number | 近7天视频带货总GMV |
| totalGmv7days | number | 近7天带货总GMV |
| videoGmv14days | number | 近14天视频带货总GMV |
| totalGmv14days | number | 近14天带货总GMV |
| medianWatchPvVideo7d | integer | 近7天视频播放中位数 |
| medianWatchPvVideo14d | integer | 近14天视频播放中位数 |
| medianWatchPvVideo28d | integer | 近28天视频播放中位数 |

**Request Demo:**

```json
{
  "creatorId": "",
  "creatorName": "",
  "creatorNickname": "",
  "creatorPortrait": "",
  "mcnContract": "",
  "creatorCategories": "",
  "ecommerceType": "",
  "creatorVerification": "",
  "description": "",
  "homepage": "",
  "email": "",
  "country": "",
  "capName": "",
  "addTime": "",
  "sourcePlatform": "",
  "fansNum": 0,
  "followers": 0,
  "likes": 0,
  "videos": 0,
  "livestreamings": 0,
  "videoGpmMin": 0.0,
  "videoGpmMax": 0.0,
  "liveGpmMin": 0,
  "liveGpmMax": 0,
  "nationalRank": 0,
  "relatedShops": 0,
  "promotedProducts": 0,
  "productCategoriesTop1": "",
  "productCategoriesTop1En": "",
  "productCategoriesProportionTop1": 0,
  "productCategoriesTop2": "",
  "productCategoriesTop2En": "",
  "productCategoriesProportionTop2": 0,
  "productCategoriesTop3": "",
  "productCategoriesTop3En": "",
  "productCategoriesProportionTop3": 0,
  "productCategoriesTop4": "",
  "productCategoriesTop4En": "",
  "productCategoriesProportionTop4": 0,
  "productCategoriesTop5": "",
  "productCategoriesTop5En": "",
  "productCategoriesProportionTop5": 0,
  "fans1824Percent": 0,
  "fans2534Percent": 0,
  "fans35AbovePercent": 0,
  "femalePercent": 0,
  "malePercent": 0,
  "recent30VideosViews": 0,
  "recent30VideosLikes": 0,
  "recent30VideosComments": 0,
  "recent30VideosShares": 0,
  "recent30DaysGmv": 0,
  "videoslikesGained7days": 0,
  "ecommerceLives7days": 0,
  "videos7days": 0,
  "followersGained7days": 0,
  "videoIpm7days": 0,
  "ecommerceVideos28days": 0,
  "productsPromotedInVideos28days": 0,
  "fansMainAge": "",
  "fansMainSex": "",
  "videoGmv28days": 0.0,
  "liveGmv28days": 0.0,
  "totalGmv28days": 0.0,
  "productEcommerceOrdersMax": 0.0,
  "crawTime": "",
  "updateTime": "",
  "totalGmvAll": 0.0,
  "ecommerceVideos": 0,
  "ecommerceVideos60days": 0,
  "recent60DaysGmv": 0.0,
  "ecommerceVideos90days": 0,
  "recent90DaysGmv": 0.0,
  "instagramUsername": "",
  "instagramLink": "",
  "youtubeLink": "",
  "youtubeChannelTitle": "",
  "boundStatus": "",
  "isCommissionDaren": 0,
  "videoGmv7days": 0.0,
  "totalGmv7days": 0.0,
  "videoGmv14days": 0.0,
  "totalGmv14days": 0.0,
  "medianWatchPvVideo7d": 0,
  "medianWatchPvVideo14d": 0,
  "medianWatchPvVideo28d": 0
}
```



> RESPONSE

**Headers:**

| name | value | required | desc |
| ------------ | ------------ | ------------ | ------------ |
| content-type | application/json;charset=UTF-8 | NO |  |

**Body:**

| name | type | desc |
| ------------ | ------------ | ------------ |
| success | boolean | 是否成功 |
| code | string | 响应码 |
| msg | string | 消息 |
| traceId | string |  |
| data | object | 数据 |
| &ensp;&ensp;&#124;─creatorId | string | 达人ID |
| &ensp;&ensp;&#124;─creatorName | string | 达人名称 |
| &ensp;&ensp;&#124;─creatorNickname | string | 达人昵称 |
| &ensp;&ensp;&#124;─creatorPortrait | string | 达人头像 |
| &ensp;&ensp;&#124;─mcnContract | string | MCN签约 |
| &ensp;&ensp;&#124;─creatorCategories | string | 达人标签 |
| &ensp;&ensp;&#124;─ecommerceType | string | 带货方式 |
| &ensp;&ensp;&#124;─creatorVerification | string | 达人认证 |
| &ensp;&ensp;&#124;─description | string | 达人简介 |
| &ensp;&ensp;&#124;─homepage | string | 主页地址 |
| &ensp;&ensp;&#124;─email | string | 邮箱 |
| &ensp;&ensp;&#124;─country | string | 达人国家 |
| &ensp;&ensp;&#124;─capName | string | 机构名称 |
| &ensp;&ensp;&#124;─addTime | string | 添加日期 |
| &ensp;&ensp;&#124;─sourcePlatform | string | 来源平台 |
| &ensp;&ensp;&#124;─fansNum | integer | 粉丝数 |
| &ensp;&ensp;&#124;─followers | integer | 累计关注数 |
| &ensp;&ensp;&#124;─likes | integer | 累计点赞数 |
| &ensp;&ensp;&#124;─videos | integer | 累计视频数 |
| &ensp;&ensp;&#124;─livestreamings | integer | 累计带货直播数 |
| &ensp;&ensp;&#124;─videoGpmMin | number | 视频GPM-最小 |
| &ensp;&ensp;&#124;─videoGpmMax | number | 视频GPM-最大 |
| &ensp;&ensp;&#124;─liveGpmMin | integer | 直播GPM-最小 |
| &ensp;&ensp;&#124;─liveGpmMax | integer | 直播GPM-最大 |
| &ensp;&ensp;&#124;─nationalRank | integer | 本国排名 |
| &ensp;&ensp;&#124;─relatedShops | integer | 累计关联店铺数 |
| &ensp;&ensp;&#124;─promotedProducts | integer | 累计带货商品数 |
| &ensp;&ensp;&#124;─productCategoriesTop1 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop1En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop1 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop2 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop2En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop2 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop3 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop3En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop3 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop4 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop4En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop4 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─productCategoriesTop5 | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesTop5En | string | 达人TOP1带货类目名称 |
| &ensp;&ensp;&#124;─productCategoriesProportionTop5 | integer | 达人TOP1带货类目占比 |
| &ensp;&ensp;&#124;─fans1824Percent | integer | 粉丝18-24年龄占比 |
| &ensp;&ensp;&#124;─fans2534Percent | integer | 粉丝25-34年龄占比 |
| &ensp;&ensp;&#124;─fans35AbovePercent | integer | 粉丝35+年龄占比 |
| &ensp;&ensp;&#124;─femalePercent | integer | 女性占比 |
| &ensp;&ensp;&#124;─malePercent | integer | 男性占比 |
| &ensp;&ensp;&#124;─recent30VideosViews | integer | 近30条视频播放数 |
| &ensp;&ensp;&#124;─recent30VideosLikes | integer | 近30条视频点赞数 |
| &ensp;&ensp;&#124;─recent30VideosComments | integer | 近30条视频评论数 |
| &ensp;&ensp;&#124;─recent30VideosShares | integer | 近30条视频分享数 |
| &ensp;&ensp;&#124;─recent30DaysGmv | integer | 近30天GMV |
| &ensp;&ensp;&#124;─videoslikesGained7days | integer | 近7日粉丝增量 |
| &ensp;&ensp;&#124;─ecommerceLives7days | integer | 近7日带货直播数 |
| &ensp;&ensp;&#124;─videos7days | integer | 近7日视频数 |
| &ensp;&ensp;&#124;─followersGained7days | integer | 近7日视频点赞增量 |
| &ensp;&ensp;&#124;─videoIpm7days | integer | 近7日视频IPM |
| &ensp;&ensp;&#124;─ecommerceVideos28days | integer | 近28天带货视频数 |
| &ensp;&ensp;&#124;─productsPromotedInVideos28days | integer | 近28天视频带货商品数 |
| &ensp;&ensp;&#124;─fansMainAge | string | 粉丝主要年龄(18-24,25-34,35+) |
| &ensp;&ensp;&#124;─fansMainSex | string | 粉丝主要性别(女,男) |
| &ensp;&ensp;&#124;─videoGmv28days | number | 近28天视频带货总GMV |
| &ensp;&ensp;&#124;─liveGmv28days | number | 近28天直播带货总GMV |
| &ensp;&ensp;&#124;─totalGmv28days | number | 近28天带货总GMV |
| &ensp;&ensp;&#124;─productEcommerceOrdersMax | number | 最大销量 |
| &ensp;&ensp;&#124;─crawTime | string | 爬取时间 |
| &ensp;&ensp;&#124;─updateTime | string |  |
| &ensp;&ensp;&#124;─totalGmvAll | number | 累计带货总GMV |
| &ensp;&ensp;&#124;─ecommerceVideos | integer | 累计带货视频数 |
| &ensp;&ensp;&#124;─ecommerceVideos60days | integer | 近60天带货视频数 |
| &ensp;&ensp;&#124;─recent60DaysGmv | number | 近60天视频带货GMV |
| &ensp;&ensp;&#124;─ecommerceVideos90days | integer | 近90天带货视频数 |
| &ensp;&ensp;&#124;─recent90DaysGmv | number | 近90天视频带货GMV |
| &ensp;&ensp;&#124;─instagramUsername | string | instagram账号名称 |
| &ensp;&ensp;&#124;─instagramLink | string | instagram主页 |
| &ensp;&ensp;&#124;─youtubeLink | string | youtube主页 |
| &ensp;&ensp;&#124;─youtubeChannelTitle | string | 所属youtube频道 |
| &ensp;&ensp;&#124;─boundStatus | string | 是否绑定(已绑定) |
| &ensp;&ensp;&#124;─isCommissionDaren | integer | 是否分佣达人:0不分佣，1分佣 |
| &ensp;&ensp;&#124;─videoGmv7days | number | 近7天视频带货总GMV |
| &ensp;&ensp;&#124;─totalGmv7days | number | 近7天带货总GMV |
| &ensp;&ensp;&#124;─videoGmv14days | number | 近14天视频带货总GMV |
| &ensp;&ensp;&#124;─totalGmv14days | number | 近14天带货总GMV |
| &ensp;&ensp;&#124;─medianWatchPvVideo7d | integer | 近7天视频播放中位数 |
| &ensp;&ensp;&#124;─medianWatchPvVideo14d | integer | 近14天视频播放中位数 |
| &ensp;&ensp;&#124;─medianWatchPvVideo28d | integer | 近28天视频播放中位数 |

**Response Demo:**

```json
{
  "success": false,
  "code": "",
  "msg": "",
  "traceId": "",
  "data": {
    "creatorId": "",
    "creatorName": "",
    "creatorNickname": "",
    "creatorPortrait": "",
    "mcnContract": "",
    "creatorCategories": "",
    "ecommerceType": "",
    "creatorVerification": "",
    "description": "",
    "homepage": "",
    "email": "",
    "country": "",
    "capName": "",
    "addTime": "",
    "sourcePlatform": "",
    "fansNum": 0,
    "followers": 0,
    "likes": 0,
    "videos": 0,
    "livestreamings": 0,
    "videoGpmMin": 0.0,
    "videoGpmMax": 0.0,
    "liveGpmMin": 0,
    "liveGpmMax": 0,
    "nationalRank": 0,
    "relatedShops": 0,
    "promotedProducts": 0,
    "productCategoriesTop1": "",
    "productCategoriesTop1En": "",
    "productCategoriesProportionTop1": 0,
    "productCategoriesTop2": "",
    "productCategoriesTop2En": "",
    "productCategoriesProportionTop2": 0,
    "productCategoriesTop3": "",
    "productCategoriesTop3En": "",
    "productCategoriesProportionTop3": 0,
    "productCategoriesTop4": "",
    "productCategoriesTop4En": "",
    "productCategoriesProportionTop4": 0,
    "productCategoriesTop5": "",
    "productCategoriesTop5En": "",
    "productCategoriesProportionTop5": 0,
    "fans1824Percent": 0,
    "fans2534Percent": 0,
    "fans35AbovePercent": 0,
    "femalePercent": 0,
    "malePercent": 0,
    "recent30VideosViews": 0,
    "recent30VideosLikes": 0,
    "recent30VideosComments": 0,
    "recent30VideosShares": 0,
    "recent30DaysGmv": 0,
    "videoslikesGained7days": 0,
    "ecommerceLives7days": 0,
    "videos7days": 0,
    "followersGained7days": 0,
    "videoIpm7days": 0,
    "ecommerceVideos28days": 0,
    "productsPromotedInVideos28days": 0,
    "fansMainAge": "",
    "fansMainSex": "",
    "videoGmv28days": 0.0,
    "liveGmv28days": 0.0,
    "totalGmv28days": 0.0,
    "productEcommerceOrdersMax": 0.0,
    "crawTime": "",
    "updateTime": "",
    "totalGmvAll": 0.0,
    "ecommerceVideos": 0,
    "ecommerceVideos60days": 0,
    "recent60DaysGmv": 0.0,
    "ecommerceVideos90days": 0,
    "recent90DaysGmv": 0.0,
    "instagramUsername": "",
    "instagramLink": "",
    "youtubeLink": "",
    "youtubeChannelTitle": "",
    "boundStatus": "",
    "isCommissionDaren": 0,
    "videoGmv7days": 0.0,
    "totalGmv7days": 0.0,
    "videoGmv14days": 0.0,
    "totalGmv14days": 0.0,
    "medianWatchPvVideo7d": 0,
    "medianWatchPvVideo14d": 0,
    "medianWatchPvVideo28d": 0
  }
}
```



