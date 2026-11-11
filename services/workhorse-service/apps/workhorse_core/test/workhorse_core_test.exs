defmodule WorkhorseCoreTest do
  use ExUnit.Case
  doctest WorkhorseCore

  test "greets the world" do
    assert WorkhorseCore.hello() == :world
  end
end
