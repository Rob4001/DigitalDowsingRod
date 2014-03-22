var osMap;
function init()
{
osMap = new OpenSpace.Map('map');
var postcodeService = new OpenSpace.Postcode();
postcodeService.getLonLat("S9 5NF", onResult);
}
function onResult(postcodeCentre)
{
osMap.setCenter(postcodeCentre, 10);
}