using ContentService.Configurations;
using ContentService.Data;
using ContentService.Repositories;
using ContentService.Services;
using Microsoft.EntityFrameworkCore;

namespace ContentService;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        // Configure the configuration environment.
        if (Environment.GetEnvironmentVariable("SPRING_CLOUD_URL") is not null)
        {
            var springCloudUrl = Environment.GetEnvironmentVariable("SPRING_CLOUD_URL");
            var environmentName = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT");

            Console.WriteLine(environmentName);
            Console.WriteLine(springCloudUrl);

            builder.Configuration.AddSpringCloud(options =>
            {
                options.Application = "content-service";
                options.Url = springCloudUrl!;
                options.Profile = environmentName!;
            });
        }

        // Add services to the container.
        builder.Services.AddControllers();

        builder.Services.AddDbContext<AppDbContext>(options =>
            options.UseNpgsql(
                builder.Configuration.GetConnectionString("PostgresConnection")));

        builder.Services.AddScoped<IPostRepository, PostRepository>();
        builder.Services.AddScoped<IPostService, PostService>();

        // Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
        builder.Services.AddOpenApi();

        var app = builder.Build();

        // Configure the HTTP request pipeline.
        if (app.Environment.EnvironmentName.StartsWith("Development"))
        {
            // Maps the native JSON endpoint (defaults to /openapi/v1.json)
            app.MapOpenApi();

            // Serve the interactive Swagger UI
            app.UseSwaggerUI(options =>
            {
                // Point Swagger UI to the native Microsoft OpenAPI document endpoint
                options.SwaggerEndpoint("/openapi/v1.json", "MediaGenerationService API v1");

                // Optional: Serves Swagger UI at the application root (localhost:port/)
                // options.RoutePrefix = string.Empty; 
            });
        }

        app.UseHttpsRedirection();

        app.UseAuthorization();


        app.MapControllers();

        app.Run();
    }
}