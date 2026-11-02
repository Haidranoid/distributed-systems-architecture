defmodule WorkhorseService.Repo do
  use Ecto.Repo,
    otp_app: :workhorse_service,
    adapter: Ecto.Adapters.Postgres
end
