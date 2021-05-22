/*function fun()
{   var x=f1.textbox1.value;
    var y=document.forms["f1"]["textbox2"].value;
	
	if (x == null || x == ""||y==null||y=="") {
        alert("above field must be filled out.");
        return false;
    }
	else
		return true;
}*/
function inputs(a)
{ //alert('hii')
  var regex=/[^a-z]/gi;	
  a.value=a.value.replace(regex,"");
}
function inputsp(a)
{ 
    var regex=/[^a-z\s]/gi;
    a.value=a.value.replace(regex,"");
}
function compare1()
{ 
	  let j=document.getElementById('car1'); 
	  for(let i=0;i<j.options.length;i++)
	  {
	      if(j.options[i].value == f1.list1.value)
	         return true;
	      	
	   }
	   alert('Enter valid car name');
	   return false;
}
function compare2()
{ 
	  let j=document.getElementById('car2'); 
	  for(let i=0;i<j.options.length;i++)
	  {	
	      if(j.options[i].value == f1.list2.value)
	         return true;
	   }
	   alert('Enter valid car name');
	   return false;
}
function goBack()
{
	  window.history.back();	
}
function view1()
{  
	var x=document.getElementById("r1").value;
	if(f1.s1.value=="thousand"){x=x/100;}
	var y=document.getElementById("r2").value;
	if(f1.s2.value=="thousand"){y=y/100; } 
	if(x>y)
    {
		alert('min must be less than max');
		return false;
    }
	f1.r1.value=x;
	f1.r2.value=y;
}
function view2()
{
 var i=f1.s.selectedIndex;
 if(i==0)
 {	 document.getElementById("b4").disabled = true;
     document.getElementById("b4").style.background='grey';
 }
 else
 {	 document.getElementById("b4").disabled = false;
 	 document.getElementById("b4").style.background='#556B2F';
 }
 f1.t1.value=f1.s.value; 
 f1.t2.value=f1.s1.options[i].value;
 document.getElementById("l1").innerHTML=(i+1)+"/"+f1.s.options.length;
 document.getElementById("l").innerHTML=f1.s.options.length +" models found ";
 if(i==(f1.s.options.length-1))
 {	 document.getElementById("b6").disabled = true;
     document.getElementById("b6").style.background='grey';
 }
 else
 {	 document.getElementById("b6").disabled = false;
 	 document.getElementById("b6").style.background='#556B2F';
 }
}
function next()
{
	  var i=f1.s.selectedIndex;
	  i=i+1;
	  f1.s.selectedIndex=i;
	  view2();  
}
function back()
{
	var i=f1.s.selectedIndex;
	i=i-1;
	f1.s.selectedIndex=i;
	view2(); 
	
}
function specification()
{ var op=f1.s;
   //alert(op.value);
	for(i=1;i<=op.options.length;i++)
	{
		document.getElementById("label"+i+"").innerHTML=op.options[i-1].value;
	}
  op.style.display = "none";
}
function signUp()
{  

	if(f1.textbox2.value.length!=10)
	{
		alert(' mobile number must contain 10 digits');
		return false;
	}	
	if(!((f1.textbox4.value)==(f1.textbox5.value)))
	{
		alert('password and confirm password must be same');
		return false;
	}
}
function addAddress()
{   
	if(f1.Zipcode.value.length!=6)
	{
		alert('zipcode must contain 6 digits');
		return false;
	}
}
function testDrive()
{   //alert('hii'+f1.time.options[0].value);
	if(f1.time.value==f1.time.options[0].value)
	{
		alert('select time slot');
		return false;
	}
  let j=document.getElementById('carName'); 
  for(let i=0;i<j.options.length;i++)
  {
      if(j.options[i].value == f1.list.value)
         return true;
      	
   }
  alert('Enter valid car name');
       return false;

    		
}
/*function a()
{
 alert(f1.t1.value);	
 f2.t1.value=f1.t1.value;
 f2.t2.value=f1.t2.value;
}*/