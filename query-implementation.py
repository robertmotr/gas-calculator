from py4j.java_gateway import JavaGateway, GatewayParameters

gateway = JavaGateway(gateway_parameters=GatewayParameters(port=19993))

query = gateway.entry_point.getQuery()

query_list = query.getQueryList()

autocomplete_input = query_list[0]

query.popAll() # clear the passed variable for future input

def URL_convert(a_string):

    a_string = '_'.join(a_string.split())

    return a_string

def get_results(url_compatible_string):

    prediction = {}

    results = []

    page = requests.get('https://maps.googleapis.com/maps/api/place/autocomplete/json?key=enterkeyhere&input=', url_compatible_string)

    content = page.content

    content = json.loads(content)

    object_num = 0

    try:

        while True:

            predictions = content['predictions'][object_num]['description']

            object_num = object_num + 1

            results.append(prediction)

    except IndexError:

        print('Ran into indexerror, finished collecting data.')

    finally:

        print('Finished collecting data.')

    return results

autocomplete_input = URL_convert(autocomplete_input)

results = get_results(autocomplete_input)

results_length = len(results)

for i in range(0, results_length):

    query.push(results[i])
