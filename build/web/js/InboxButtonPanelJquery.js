 $(document).ready(function(){
              
              
               $('.c').click(function(){
                   if($('.c:checked').length===1) {
               $("#panel").show("slow");
                    } else {
                $("#panel").hide("slow");
                }
                
                var a = $('.c:checked').val();
                var id = "#"+a;
                //alert(id);
                var d = $(id).val();
                var fwd = "#"+a+"f";
                var to = "#"+a+"t";
                var f = $(fwd).val();
               // alert("Fwd by "+f);
                var t = $(to).val();
               // alert("User "+t);
                
                if(f === "NONE" && d==="pending")
                {
                    $('#a').attr('disabled',false);
                    $('#r').attr('disabled',false);
                    $('#f').attr('disabled',true);
                }
                else if(f !== t && d==="pending")
                {
                    
                    $('#a').attr('disabled',false);
                    $('#r').attr('disabled',false);
                    $('#f').attr('disabled',false);
                }
                else if(f === t && d==="Forwarded")
                {
                    $('#a').attr('disabled',true);
                    $('#r').attr('disabled',true);
                    $('#f').attr('disabled',true);
                   
                }
                
                else if(f !== t && d==="Forwarded")
                {
                    $('#a').attr('disabled',false);
                    $('#r').attr('disabled',false);
                    $('#f').attr('disabled',true);
                   
                }
                else if(f === t && d === "pending")
                {
                    $('#a').attr('disabled',true);
                    $('#r').attr('disabled',false);
                    $('#f').attr('disabled',false);
                }
                
                else
                {
                    $('#a').attr('disabled','disabled');
                    $('#r').attr('disabled','disabled');
                    $('#f').attr('disabled','disabled');
                }
                
               });
                      
            });