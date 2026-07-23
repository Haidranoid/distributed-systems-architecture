namespace ContentService.Configurations;

public sealed class SpringCloudConfigurationSource: IConfigurationSource
{
    public string Url { get; set; } = string.Empty;
    public string Application { get; set; } = string.Empty;
    public string Profile { get; set; } = string.Empty;

    public IConfigurationProvider Build(IConfigurationBuilder builder)
    {
        return new SpringCloudConfigurationProvider(this);
    }
}