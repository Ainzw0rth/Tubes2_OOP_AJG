DB_TABLES := customers items members bills fixed_bills plugin_paths

JSON_DIR := JSON
XML_DIR := XML
OBJ_DIR := OBJ

JSON_FILES := $(foreach table, $(DB_TABLES), database/$(JSON_DIR)/$(table).json)
XML_FILES := $(foreach table, $(DB_TABLES), database/$(XML_DIR)/$(table).xml)

db:
	@echo "[1] 	Creating database"
	mkdir -p database
	@echo "[2] 	Creating directories"
	mkdir -p database/$(JSON_DIR) database/$(XML_DIR) database/$(OBJ_DIR)
	@echo "[3] 	Creating JSON files"
	touch $(JSON_FILES)
	@echo "[4] 	Creating XML files"
	touch $(XML_FILES)

	@echo ""
	@echo ""
	@echo "@SUCCESS@	Database created."