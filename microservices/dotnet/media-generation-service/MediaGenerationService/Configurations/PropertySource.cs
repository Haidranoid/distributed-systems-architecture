using System.Text.Json;

namespace MediaGenerationService.Configurations;

public sealed class PropertySource
{
    public string Name { get; set; } = string.Empty;
    public Dictionary<string, JsonElement> Source { get; set; } = [];
}