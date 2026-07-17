using MediaGenerationService.Data;
using MediaGenerationService.Repositories;
using MediaGenerationService.Services;
using Microsoft.EntityFrameworkCore;

namespace MediaGenerationService;

public class Program
{
    public static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);

        // Add services to the container.
        builder.Services.AddControllers();

        builder.Services.AddDbContext<AppDbContext>(options =>
            options.UseNpgsql(
                builder.Configuration.GetConnectionString("PostgresConnection")));
        
        builder.Services.AddScoped<IMediaQueryRepository, MediaQueryRepository>();
        builder.Services.AddScoped<IMediaQueryService, MediaQueryService>();
        
        // Learn more about configuring OpenAPI at https://aka.ms/aspnet/openapi
        builder.Services.AddOpenApi();

        var app = builder.Build();

        // Configure the HTTP request pipeline.
        if (app.Environment.IsDevelopment())
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