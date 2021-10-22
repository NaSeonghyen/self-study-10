function productCheck() {
  if (document.frm.mtitle.value.length == 0) {
    alert("제목을 입력하세요");
    frm.mtitle.focus();
    return false;
  }
  if (document.frm.price.value.length == 0) {
    alert("가격을 입력하세요");
    frm.price.focus();
    return false;
  }
  if (isNaN(document.frm.price.value)) {
    alert("가격은 숫자로 입력해야 합니다.");
    frm.price.focus();
    return false;
  }
  return true;
}
