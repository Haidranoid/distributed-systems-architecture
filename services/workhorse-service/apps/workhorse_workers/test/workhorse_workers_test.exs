defmodule WorkhorseWorkersTest do
  use ExUnit.Case
  doctest WorkhorseWorkers

  test "greets the world" do
    assert WorkhorseWorkers.hello() == :world
  end
end
