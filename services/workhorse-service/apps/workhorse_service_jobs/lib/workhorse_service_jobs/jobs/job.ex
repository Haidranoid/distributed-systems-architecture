defmodule WorkhorseServiceJobs.Jobs.Job do
  use Ecto.Schema
  import Ecto.Changeset

  schema "jobs" do
    field(:name, :string)
    field(:status, :string)
    field(:scheduled_at, :utc_datetime)
    field(:started_at, :utc_datetime)
    field(:completed_at, :utc_datetime)

    timestamps()
  end

  @doc false
  def changeset(job, attrs) do
    job
    |> cast(attrs, [:name, :status, :scheduled_at, :started_at, :completed_at])
    |> validate_required([:name, :status])

    # |> validate_required([:name, :status, :scheduled_at, :started_at, :completed_at])
  end
end
