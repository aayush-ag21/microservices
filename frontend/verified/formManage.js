function openadd(){
    closeupdate();
    closedelete();
    document.getElementById("addform").style.display = "block";
    document.getElementById("add_username").focus();
}
function closeadd(){
    document.getElementById("add-container").reset();
    document.getElementById("addform").style.display = "none";
}

function openupdate(){
    closeadd();
    closedelete();
    document.getElementById("updateform").style.display = "block";
    document.getElementById("update_username").focus();
}
function closeupdate(){
    document.getElementById("update-container").reset();
    document.getElementById("updateform").style.display = "none";
}

function opendelete(){
    closeadd();
    closeupdate();
    document.getElementById("deleteform").style.display = "block";
    document.getElementById("delete_username").focus();
}
function closedelete(){
    document.getElementById("delete-container").reset();
    document.getElementById("deleteform").style.display = "none";
}

