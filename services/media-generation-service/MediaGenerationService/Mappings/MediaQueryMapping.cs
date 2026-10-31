using MediaGenerationService.DTOs;
using MediaGenerationService.Models;

namespace MediaGenerationService.Mappings;

public static class MediaQueryMapping
{
    public static MediaQueryDto ToDto(MediaQuery mediaQuery)
    {
        return new MediaQueryDto
        {
            Id = mediaQuery.Id,
            PositivePrompt = mediaQuery.PositivePrompt
        };
    }

    public static IEnumerable<MediaQueryDto> ToDto(IEnumerable<MediaQuery> mediaQueries)
    {
        return mediaQueries.Select(ToDto);
    }
}