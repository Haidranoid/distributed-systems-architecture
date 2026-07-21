using System.Text.Json;

namespace MediaGenerationService.Configurations;

public sealed class SpringCloudConfigurationProvider : ConfigurationProvider
{
    private readonly SpringCloudConfigurationSource _source;

    public SpringCloudConfigurationProvider(SpringCloudConfigurationSource source)
    {
        _source = source;
    }

    public override void Load()
    {
        using var client = new HttpClient();

        //Console.WriteLine($"{_source.Url}/{_source.Application}/{_source.Profile}");

        var response = client
            .GetFromJsonAsync<ConfigResponse>(
                $"{_source.Url}/{_source.Application}/{_source.Profile}"
            )
            .GetAwaiter()
            .GetResult();

        if (response is null)
            return;

        //Console.WriteLine(response.ToJsonPretty());

        foreach (var propertySource in response.PropertySources)
        {
            foreach (var pair in propertySource.Source)
            {
                Data[pair.Key.Replace('.', ':')] = pair.Value.ToString();
            }
        }

        //Console.WriteLine(Data.ToJsonPretty());
    }
}