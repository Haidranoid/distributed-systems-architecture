using System.Text.Json;

namespace ContentService.Configurations;

public static class JsonExtensions
{
    private static readonly JsonSerializerOptions Pretty = new()
    {
        WriteIndented = true,
    };
    
    public static string ToJsonPretty(this object? value) => JsonSerializer.Serialize(value, Pretty);
}