defmodule WorkhorseServiceCoreTest do
  use ExUnit.Case
  doctest WorkhorseServiceCore

  test "greets the world" do
    assert WorkhorseServiceCore.hello() == :world
  end
end
