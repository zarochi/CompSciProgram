<?php
//phpinfo(); //shows server info
$length=$_POST['length'];//obtained from server info
$width=$_POST['width'];
function calcArea()
{
if (!is_numeric($length)||$length<0||intval($length) != floatval($length))
{
	echo "Invalid length";
}
else if (!is_numeric($width)||$width<0||intval($width) != floatval($width))
{
	echo "Invalid width";
}
else
{
$area=$length*$width;
echo "Length is: ".$length."<br/>\n";
echo "Width is: ".$width."<br/>\n";
echo "Area is: ".$area."<br/>\n";
}
}
?>
