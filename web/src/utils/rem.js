// 配置基本大小
let baseSize = 24;

// 设置 rem 函数
function setRem() {
  // console.log('width===>', document.documentElement.clientWidth);
  //当前页面宽度相对于1920px屏幕宽的缩放比例，可根据自己需要修改。
  let scale = document.documentElement.clientWidth / 1920;
  //设置页面根节点字体大小（“Math.min(scale, 2)” 指最高放大比例为2，可根据实际业务需求调整）
  let size = baseSize * Math.min(scale, 2);
  if (size <= 14) {
    size = 14;
  }
  document.documentElement.style.fontSize = size + 'px';
}
setRem(); //初始化

// 适配 - 重置函数
function resetRem(num) {
  if (num) baseSize = Number(num);
  setRem();
}
window.resetRem = resetRem; // 全局可调用(其他方式也可)

// 改变窗口大小时重置 rem
window.onresize = function () {
  setRem();
};
