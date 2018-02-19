/**
 * Created by maurice on 5/17/17.
 * Ref: https://bootsnipp.com/snippets/featured/easy-table-filter
 */
$(document).ready(function () {

    $('.star').on('click', function () {
        $(this).toggleClass('star-checked');
    });

    $('.ckbox label').on('click', function () {
        $(this).parents('tr').toggleClass('selected');
    });
});

/*
 * Send request to checkout controller to modify the book status
 */
function checkOut(element, id, urlString){
    var status = element.checked;
    if(status){//dynamically change checkbox label text
        $("#" + id).html("Due Date:");
    }else{
        $("#" + id).html("Checked In");
        $("#due" + id).html("");
    }
    //update status in database - using ajax post request

    var url = urlString + "/checkout";
    $.post(url, {status:status, id:id}, function(data){
        //process response
        $("#due" + id).append(getDateString(data.dueDate));
    });
}

/*
 * Convert date to standard date string for display in view
 */
function getDateString(data) {
    var date = ""
    if(data){
        date = (new Date(data)).toDateString();
    }
    return date;
}