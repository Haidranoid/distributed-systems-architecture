defmodule WorkhorseServiceJobs.Repo do
  use Ecto.Repo,
    otp_app: :workhorse_service_jobs,
    adapter: Ecto.Adapters.Postgres
end
