defmodule GameTest do
  use ExUnit.Case
  @unexisting_session_id "fcfe5f21-8a08-4c9a-9f97-29d2fd6a27b9"
  @unexisting_game_id "e2f67065-f85e-4815-8b39-7df44a80db06"

  describe "POST /game - correct authorization" do
    test "returns 201 Created response" do
      session_id = Session.get_session_id()
      headers = Game.set_authorized_headers(session_id)

      {:ok, %{status_code: status_code, body: body}} = Game.post_response(headers)
      data = Poison.decode!(body)["data"]

      assert status_code == 201
      assert data["type"] == "Game"
      assert data["attributes"]["cards"] |> length() == 30
    end
  end

  describe "POST /game - incorrect authorization" do
    test "returns 401 Unauthorized response" do
      headers = Game.set_unauthorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} = Game.post_response(headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 401
      assert errors["status"] == "401"
      assert errors["title"] == "Failed to pass authorization level"
    end
  end

  describe "POST /game - session does not exist" do
    test "returns 404 Not Found response" do
      headers = Game.set_authorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} = Game.post_response(headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 404
      assert errors["status"] == "404"
      assert errors["title"] == "Session not found"
    end
  end

  describe "GET /game - correct authorization and session exists" do
    test "returns 200 OK response" do
      session_id = Session.get_session_id()
      game_id = Game.get_game_id(session_id)

      headers = Game.set_authorized_headers(session_id)

      {:ok, %{status_code: status_code, body: body}} = Game.get_response(game_id, headers)
      data = Poison.decode!(body)["data"]

      assert status_code == 200
      assert data["type"] == "Game"
      assert data["id"] == game_id
    end
  end

  describe "GET /game - incorrect authorization" do
    test "returns 401 Unauthorized response" do
      headers = Game.set_unauthorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} =
        Game.get_response(@unexisting_game_id, headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 401
      assert errors["status"] == "401"
      assert errors["title"] == "Failed to pass authorization level"
    end
  end

  describe "GET /game - correct authorization and session does not exist" do
    test "returns 404 Not Found response" do
      headers = Game.set_authorized_headers(@unexisting_session_id)

      {:ok, %{status_code: status_code, body: body}} =
        Game.get_response(@unexisting_game_id, headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 404
      assert errors["status"] == "404"
      assert errors["title"] == "Session not found"
    end
  end

  describe "GET /game - correct authorization and game does not exist" do
    test "returns 404 Not Found response" do
      headers = Game.set_authorized_headers(Session.get_session_id())

      {:ok, %{status_code: status_code, body: body}} =
        Game.get_response(@unexisting_game_id, headers)

      errors = Poison.decode!(body)["errors"] |> List.first()

      assert status_code == 404
      assert errors["status"] == "404"
      assert errors["title"] == "Game not found"
    end
  end
end
