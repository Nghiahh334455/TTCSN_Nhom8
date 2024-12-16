window.onload = function() {
  const errorMessage = document.querySelector('.alert');
  if (errorMessage) {
    setTimeout(() => {
      errorMessage.style.display = 'none';
    }, 5000); // Ẩn thông báo sau 5 giây
  }
};
