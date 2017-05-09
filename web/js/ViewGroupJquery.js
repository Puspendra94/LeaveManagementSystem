$('document').ready(function(){
                $('.c').click(function(){
                    if($('.c:checked').length===1)
                    {
                        $('#panel').show("slow");
                    }
                    else
                    {
                        $('#panel').hide("slow");
                    }
                    
                    var a = $('.c:checked').val();
                    
                    if(a==="Default")
                    {
                       $('#d').attr('disabled',true); 
                       $('#u').attr('disabled',true); 
                    }
                    else
                    {
                        $('#d').attr('disabled',false);
                        $('#u').attr('disabled',false);
                    }
                });
                
                
            });

