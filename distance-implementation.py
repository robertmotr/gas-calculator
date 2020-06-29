#note: i realize that there is unnecessary casting but do keep in mind that this script was made in <5 minutes.
#furthermore, the script works as intended without issues. "if it aint broke, dont fix it".

from py4j.java_gateway import JavaGateway, GatewayParameters
import requests
import json

gateway = JavaGateway(gateway_parameters=GatewayParameters(port=19992))

distance = gateway.entry_point.getDistance()

distance_list = distance.getDistanceList()

origin = distance_list[0]

destination = distance_list[1]

distance.popAll()

def URL_convert(a_string):

    a_string = '_'.join(a_string.split())

    return a_string

def get_results(url_origin, url_destination):

    page = requests.get('https://maps.googleapis.com/maps/api/distancematrix/json?units=metric' + '&origins=' + str(url_origin) +'&destinations='+ str(url_destination) + '&key=enterkeyhere')

    content = page.content

    content = json.loads(content)

    distance = content['rows'][0]['elements'][0]['distance']['text']

    return distance

origin = URL_convert(origin)

destination = URL_convert(destination)

distance_units = get_results(origin, destination, metric_boolean)

distance.push(distance_units)





