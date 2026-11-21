defmodule WorkhorseServiceWeb.Accounts.Scope do
  @enforce_keys [:user_id]

  defstruct [
    :user_id,
    :username,
    :role,
    permissions: []
  ]
end
