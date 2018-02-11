defmodule ParserTest do
  use ExUnit.Case

  @title1 "Like A Virgin"
  @title2 "Isla Bonita"

  describe "retrive_names/1" do
    test "returns titles from body" do
      body = %{
        "query" => %{
          "categorymembers" => [
            %{
              "title" => @title1
            },
            %{
              "title" => @title2
            }
          ]
        }
      }

      names = Scraper.Parser.retrieve_names(body) |> Enum.to_list()
      assert names == [@title1, @title2]
    end

    test "sanitize inputs from body" do
      body = %{
        "query" => %{
          "categorymembers" => [
            %{
              "title" => "Sean Connery (actor)"
            },
            %{
              "title" => "Madonna (whatever)"
            }
          ]
        }
      }

      names = Scraper.Parser.retrieve_names(body) |> Enum.to_list()
      assert names == ["Sean Connery", "Madonna"]
    end
  end
end
