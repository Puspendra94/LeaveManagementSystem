
            $(document).ready(function(){
                $(".c").click(function(){
                    if($('.c:checked').length===1)
                    {
                        $("#panel").show("slow");
                    }
                    else
                    {
                        $("#panel").hide("slow");
                    }
                    
                    var a = $('.c:checked').val();
                    var id = "#"+a;
                    var as = $(id).val();
                    
                    if(as === "pending")
                    {
                        $('#e').attr('disabled',false);
                        $('#c').attr('disabled',false);
                        $('#d').attr('disabled',false);
                    }
                    else
                    {
                        $('#e').attr('disabled',true);
                        $('#c').attr('disabled',true);
                        $('#d').attr('disabled',false);
                    }
                });
            });
    