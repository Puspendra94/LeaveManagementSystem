$(document).ready(function(){
                $("#start,#end").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    firstDay: 1,
                    dateFormat: 'dd/mm/yy',
                   // minDate: 0
                });
               /* $("#start").datepicker({
                    dateFormat: 'dd-mm-yyyy'
                });
                $("end").datepicker({
                    dateFormat: 'dd-mm-yyyy'
                });*/
                $("#end").change(function(){
                    var s = $("#start").datepicker('getDate');
                    var e = $("#end").datepicker('getDate');
                    
                    if(s===null)
                    {
                        alert("Please entered (From Date) first");
                        $('#start').val("");
                        $('#end').val("");
                        $("#day").val("");
                    }
                    else if(s<e)
                    {
                        var day = (e-s)/1000/60/60/24;
                        $("#day").val(day);
                    }
                    else
                    {
                        //var a = "You have entered an invalid (To date)";
                        //document.getElementById("msg").innerHTML = a;
                        //$('#msg').val("You have entered an invalid (To date)To date must be less than From date");
                        alert("You have entered an invalid (To date)\n\n\nTo date must be greater than From date");
                        $('#start').val("");
                        $('#end').val("");
                        $('#day').val("");
                    }
                });
                
                $('#emsg,#cmsg').click(function(){
                    $('#emsg').hide(1000);
                    $('#cmsg').hide(1000);
                });
                
            });