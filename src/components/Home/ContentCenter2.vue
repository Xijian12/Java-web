<template>
  <div class="contentcenter">
    <div class="contentcenter_frame">
      <span class="contentcenter_frame_title">
        ifashion <span class="contentcenter_frame_title_span">時尚穿搭</span>
      </span>
      <div class="contentcenter_frame_image">
        <div class="contentcenter_frame_image_big">
          <img src="../../assets/img/huandengpian.jpg" alt="" />
        </div>
        <div class="contentcenter_frame_image_small">
          <div class="contentcenter_frame_image_small_1">
            <img src="../../assets/img/image1.png" alt="" />
            <img src="../../assets/img/image2.png" alt="" />
          </div>
        </div>
      </div>
      <div class="contentcenter_frame_com">
        <div class="contentcenter_frame_com_image">
          <img src="../../assets/img/login_image1.png" alt="" />
        </div>
        <div class="contentcenter_frame_com_wenzi">
          <div class="contentcenter_frame_com_wenzi_title">达人推荐</div>
          <div class="contentcenter_frame_com_wenzi_content">
            <img
              src="../../assets/img/login_image1.png"
              alt=""
            />年轻人的潮流推荐
          </div>
        </div>
        <div class="contentcenter_frame_com_symbol">
            <div>
                >
            </div>
        </div>
      </div>
    </div>
    <div class="contentcenter_frame">
      <span class="contentcenter_frame_title">
        ifashion <span class="contentcenter_frame_title_span">時尚穿搭</span>
      </span>
      <div class="contentcenter_frame_image">
        <div class="contentcenter_frame_image_big">
          <img src="../../assets/img/huandengpian.jpg" alt="" />
        </div>
        <div class="contentcenter_frame_image_small">
          <div class="contentcenter_frame_image_small_1">
            <img src="../../assets/img/image1.png" alt="" />
            <img src="../../assets/img/image2.png" alt="" />
          </div>
        </div>
      </div>
      <div class="contentcenter_frame_com">
        <div class="contentcenter_frame_com_image">
          <img src="../../assets/img/login_image1.png" alt="" />
        </div>
        <div class="contentcenter_frame_com_wenzi">
          <div class="contentcenter_frame_com_wenzi_title">达人推荐</div>
          <div class="contentcenter_frame_com_wenzi_content">
            <img
              src="../../assets/img/login_image1.png"
              alt=""
            />年轻人的潮流推荐
          </div>
        </div>
        <div class="contentcenter_frame_com_symbol">
            <div>
                >
            </div>
        </div>
      </div>
    </div>
    <div class="contentcenter_frame">
      <span class="contentcenter_frame_title">
        ifashion <span class="contentcenter_frame_title_span">時尚穿搭</span>
      </span>
      <div class="contentcenter_frame_image">
        <div class="contentcenter_frame_image_big">
          <img src="../../assets/img/huandengpian.jpg" alt="" />
        </div>
        <div class="contentcenter_frame_image_small">
          <div class="contentcenter_frame_image_small_1">
            <img src="../../assets/img/image1.png" alt="" />
            <img src="../../assets/img/image2.png" alt="" />
          </div>
        </div>
      </div>
      <div class="contentcenter_frame_com">
        <div class="contentcenter_frame_com_image">
          <img src="../../assets/img/login_image1.png" alt="" />
        </div>
        <div class="contentcenter_frame_com_wenzi">
          <div class="contentcenter_frame_com_wenzi_title">达人推荐</div>
          <div class="contentcenter_frame_com_wenzi_content">
            <img
              src="../../assets/img/login_image1.png"
              alt=""
            />年轻人的潮流推荐
          </div>
        </div>
        <div class="contentcenter_frame_com_symbol">
            <div>
                >
            </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
export default {
  name: "ContentCenter2",
  data() {
    return {};
  },
};
</script>

<style scoped>
.contentcenter {
  position: relative;
  width: 1200px;
  height: 400px;
  background-color: #fff;
  left: 50%;
  transform: translate(-50%);
  display: flex;
  justify-content: space-around;
  border-radius: 12px;
  margin-top: 20px;
}
.contentcenter_frame {
  width: 355px;
  height: 100%;
  float: left;
}
.contentcenter_frame_title {
  height: 80px;
  width: 100%;
  font-size: 25px;
  line-height: 80px;
  margin-left: 20px;
}
.contentcenter_frame_title_span {
  padding: 0 5px;
  font-size: 15px;
  background-color: cornflowerblue;
  color: #fff;
  border-radius: 3px;
}
.contentcenter_frame_image {
  width: 100%;
  height: 235px;
  /* background-color: crimson; */
  display: flex;
  justify-content: space-between;
}
.contentcenter_frame_image_big {
  width: 235px;
  height: 100%;
}
.contentcenter_frame_image_big > img {
  width: 100%;
  height: 100%;
  border-radius: 12px;
}
.contentcenter_frame_image_small {
  width: 115px;
  height: 100%;
}
.contentcenter_frame_image_small_1 {
  width: 100%;
  height: 115px;
}
.contentcenter_frame_image_small_1 > img {
  width: 100%;
  height: 100%;
  border-radius: 12px;
}
.contentcenter_frame_com {
  margin-top: 16px;
  width: 100%;
  height: 53px;
  display: flex;
}
.contentcenter_frame_com_image {
  flex: 2;
  width: 40px;
  height: 100%;
}
.contentcenter_frame_com_image > img {
  width: 46px;
  height: 46px;
  position: relative;
  top: 50%;
  left: 0;
  transform: translate(0, -50%);
}
.contentcenter_frame_com_wenzi {
  flex: 7;
  width: 40px;
  height: 100%;
}
.contentcenter_frame_com_wenzi_title {
  font-size: 17px;
}
.contentcenter_frame_com_wenzi_content > img {
  margin-right: 10px;
  width: 16px;
  height: 16px;
  position: relative;
  top: 3px;
}
.contentcenter_frame_com_wenzi_content {
  color: slateblue;
  font-size: 13px;
  line-height: 35px;
}
.contentcenter_frame_com_symbol {
  flex: 1;
  width: 40px;
  height: 100%;
  line-height: 46px;
  text-align: center;
}
.contentcenter_frame_com_symbol > div{
    color: slateblue;
    font-size: 40px;
}
</style>