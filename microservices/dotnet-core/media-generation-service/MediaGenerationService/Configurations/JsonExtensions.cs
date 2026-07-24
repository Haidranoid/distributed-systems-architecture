using System.Text.Json;

namespace MediaGenerationService.Configurations;

public static class JsonExtensions
{
    private static readonly JsonSerializerOptions Pretty = new()
    {
        WriteIndented = true,
    };
    
    public static string ToJsonPretty(this object? value) => JsonSerializer.Serialize(value, Pretty);
}