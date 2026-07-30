namespace MediaGenerationService.Configurations;

public static class SpringCloudExtensions
{
    public static IConfigurationBuilder AddSpringCloud(
        this IConfigurationBuilder builder,
        Action<SpringCloudConfigurationSource> configure)
    {
        var source = new SpringCloudConfigurationSource();

        configure(source);

        builder.Add(source);

        return builder;
    }
}