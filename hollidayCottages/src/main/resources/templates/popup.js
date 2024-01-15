document.addEventListener("DOMContentLoaded", function () {
    const showLoginButton = document.getElementById("show-login");
    const popup = document.querySelector(".popup");
    const closeBtn = document.querySelector(".popup .close-btn");

    let isOpen = false;

    showLoginButton.addEventListener("click", function () {
        if (!isOpen) {
            const topPosition = 70 + showLoginButton.offsetHeight;
            popup.style.top = `${topPosition}px`;
            popup.style.left = "calc(100% - 420px)";
            popup.classList.add("active");
            isOpen = true;
        }
    });

    closeBtn.addEventListener("click", function () {
        popup.style.transition = "top 200ms ease-in-out";
        popup.style.top = "-150%";

        setTimeout(function () {
            popup.style.transition = "";
            popup.classList.remove("active");
            isOpen = false;
        }, 200);
    });
});
